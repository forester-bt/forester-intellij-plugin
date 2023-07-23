package com.github.besok.foresterintellijplugin.parser;

import com.github.besok.foresterintellijplugin.gramma.TreeParser;
import com.github.besok.foresterintellijplugin.parser.nodes.Id;
import com.intellij.lang.DefaultASTFactoryImpl;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;

import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.tt;

public class FTreeAstFactory extends DefaultASTFactoryImpl  {
    @Override
    public @NotNull LeafElement createLeaf(@NotNull IElementType type, @NotNull CharSequence text) {
        if(type instanceof TokenIElementType tpe){
            if(tt(TreeParser.ID) == tpe)
                return new Id(type,text);
        }
        return super.createLeaf(type, text);
    }
}
