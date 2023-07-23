package com.github.besok.foresterintellijplugin.highlight;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.github.besok.foresterintellijplugin.gramma.TreeLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.v4.runtime.CharStreams;
import org.jetbrains.annotations.NotNull;

import static com.github.besok.foresterintellijplugin.gramma.TreeLexer.*;

public class FTreeSyntaxHighlighter extends SyntaxHighlighterBase {

    static TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ANTLRLexerAdaptor(FTreeLanguage.INSTANCE, new TreeLexer(CharStreams.fromString("")));
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType instanceof TokenIElementType) {
            return switch (((TokenIElementType) tokenType).getANTLRTokenType()) {
                case ID -> FTreeSyntaxHighlighterKeys.ID;
                case DOT_DOT -> FTreeSyntaxHighlighterKeys.DOT;
                case EQ -> FTreeSyntaxHighlighterKeys.SIGN;
                case STRING ->  FTreeSyntaxHighlighterKeys.STRING;
                case LBR , RBR -> FTreeSyntaxHighlighterKeys.BRACES;
                case LBC, RBC -> FTreeSyntaxHighlighterKeys.BRACKETS;
                case LPR, RPR -> FTreeSyntaxHighlighterKeys.PARENTHESES;
                case LineComment  , BlockComment -> FTreeSyntaxHighlighterKeys.COMMENTS;
                case NUMBER -> FTreeSyntaxHighlighterKeys.NUMBERS;
                case ARRAY_T,NUM_T,OBJECT_T,STRING_T,BOOL_T,TREE_T -> FTreeSyntaxHighlighterKeys.SPECIAL_SYMBOLS;
                case ROOT, PARALLEL,SEQUENCE,MSEQUENCE,RSEQUENCE,FALLBACK,RFALLBACK,IMPORT,TRUE,FALSE ->
                        FTreeSyntaxHighlighterKeys.KEYWORDS;
                default -> EMPTY_KEYS;
            };
        } else return EMPTY_KEYS;
    }
}
