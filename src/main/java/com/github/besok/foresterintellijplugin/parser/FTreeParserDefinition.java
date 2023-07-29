package com.github.besok.foresterintellijplugin.parser;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.github.besok.foresterintellijplugin.gramma.TreeLexer;
import com.github.besok.foresterintellijplugin.gramma.TreeParser;
import com.github.besok.foresterintellijplugin.parser.nodes.*;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.runtime.tree.Tree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jetbrains.annotations.NotNull;

import static com.github.besok.foresterintellijplugin.gramma.TreeParser.*;
import static org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory.createTokenSet;
import static org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory.defineLanguageIElementTypes;

public class FTreeParserDefinition implements ParserDefinition {
    public FTreeParserDefinition() {
        defineLanguageIElementTypes(FTreeLanguage.INSTANCE, TreeLexer.tokenNames, TreeParser.ruleNames);
    }

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new ANTLRLexerAdaptor(FTreeLanguage.INSTANCE, new TreeLexer(CharStreams.fromString("")));
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new FTreeParserAdaptor(new TreeParser(new CommonTokenStream(new TreeLexer(CharStreams.fromString("")))));
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return new IFileElementType(FTreeLanguage.INSTANCE);
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return createTokenSet(FTreeLanguage.INSTANCE, TreeLexer.LineComment);
    }

    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return createTokenSet(FTreeLanguage.INSTANCE, TreeLexer.Whitespace);
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return createTokenSet(FTreeLanguage.INSTANCE, TreeLexer.STRING);
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        IElementType elementType = node.getElementType();
        if (elementType instanceof RuleIElementType) {
            int ruleIndex = ((RuleIElementType) elementType).getRuleIndex();
            return switch (ruleIndex) {
                case RULE_importSt -> new ImportRecord(node, elementType);
                case RULE_static_type -> new StaticType(node, elementType);
                case RULE_definition -> new TreeDef(node, elementType);
                case RULE_call -> new Call(node, elementType);
                case RULE_lambda -> new Lambda(node, elementType);
                case RULE_invocation -> new Invocation(node, elementType);
                case RULE_arg -> new Arg(node, elementType);
                default -> new ANTLRPsiNode(node);
            };
        } else {
            return new ANTLRPsiNode(node);
        }
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new FTreeFile(viewProvider);
    }
}
