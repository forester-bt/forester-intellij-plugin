package com.github.besok.foresterintellijplugin.parser;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import com.github.besok.foresterintellijplugin.gramma.TreeParser;
public class FTreeParserAdaptor extends ANTLRParserAdaptor{

    public FTreeParserAdaptor(TreeParser parser) {
        super(FTreeLanguage.INSTANCE, parser);
    }

    @Override
    protected ParseTree parse(Parser parser, IElementType root) {
        return ((TreeParser) parser).file();
    }
}