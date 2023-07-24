package com.github.besok.foresterintellijplugin.format;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.github.besok.foresterintellijplugin.gramma.TreeLexer;
import com.github.besok.foresterintellijplugin.parser.FTreeAstUtils;
import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;

import static com.github.besok.foresterintellijplugin.parser.FTreeAstUtils.tt;

public class FTreeFormattingBuilder implements FormattingModelBuilder {
    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, FTreeLanguage.INSTANCE)
                .around(tt(TreeLexer.EQ)).spaces(1)
                .before(tt(TreeLexer.LBR)).spaces(1)
                .before(tt(TreeLexer.LBC)).spaces(1)
                ;
    }

    @Override
    public FormattingModel createModel( FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new BaseBlock(formattingContext.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(codeStyleSettings)),
                        codeStyleSettings);
    }
}
