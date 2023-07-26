package com.github.besok.foresterintellijplugin.highlight;

import com.github.besok.foresterintellijplugin.parser.FTreeAstUtils;
import com.github.besok.foresterintellijplugin.parser.nodes.Invocation;
import com.github.besok.foresterintellijplugin.parser.nodes.TreeDef;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.psi.PsiElement;

import java.util.List;
import java.util.Optional;

public class FTreeAnnotator implements Annotator {
    @Override
    public void annotate(final PsiElement element, AnnotationHolder holder) {
        if (element instanceof Invocation) {
            var ho = FTreeAstUtils.ast(element).path("/invocation/args");
            if (ho.isEmpty()) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(element.getTextRange()).textAttributes(DefaultLanguageHighlighterColors.HIGHLIGHTED_REFERENCE).create();
            }

            FTreeAstUtils.ast(element).id().ifPresent((id) -> {
                if (List.of("inverter", "force_success", "force_fail", "repeat", "retry", "timeout", "delay").contains(id.getText())) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(id.getTextRange()).textAttributes(DefaultLanguageHighlighterColors.CLASS_REFERENCE).create();
                }
            });
        }
        if (element instanceof TreeDef) {
            Optional<? extends PsiElement> typeElem = FTreeAstUtils.ast(element).path("/definition/tree_type").stream().findFirst();
            var type = typeElem.map(PsiElement::getText).orElse("");

            if (type.equals("cond") || type.equals("impl")) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(typeElem.get().getTextRange()).textAttributes(FTreeSyntaxHighlighterKeys.KEYWORDS[0]).create();
                FTreeAstUtils.ast(element).id().ifPresent((id) -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(id.getTextRange()).textAttributes(DefaultLanguageHighlighterColors.FUNCTION_DECLARATION).create());
            }
        }
    }
}
