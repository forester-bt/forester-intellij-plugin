package com.github.besok.foresterintellijplugin.highlight;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class FTreeColorSettingsPage implements ColorSettingsPage {
    @Override
    public @Nullable Icon getIcon() {
        return FTreeLanguage.ICON_LIGHT;
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new FTreeSyntaxHighlighter();
    }

    @Override
    public @NonNls @NotNull String getDemoText() {
        return """
                import "nested/impls.tree"
                import "nested/impls.tree" {
                    grasp => grasp_ball,
                }
                                
                root place_ball_to_target fallback {
                    place_to(
                        obj = {"x":1 },
                        operation = place([10]),
                    )
                    retry(5) ask_for_help()
                }
                                
                sequence place_to(what:object, operation:tree){
                    fallback {
                        is_approachable(what)
                        do_job(approach(what))
                    }
                    fallback {
                         is_graspable(what)
                         do_job(approach(what))
                    }
                    sequence {
                         savepoint()
                         operation(..)
                    }
                }
                                
                sequence place(where:array){
                    is_valid_place(where)
                    do_job(slowly_drop({"cord":1}))
                }
                                
                sequence do_job(action:tree){
                    savepoint()
                    info_wrapper(action(..))
                    savepoint()
                }
                                
                sequence info_wrapper(action:tree){
                    log("before action")
                    action(..)
                    log("before action")
                }
                                
                impl log(text:string);
                """;
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return new AttributesDescriptor[0];
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return new ColorDescriptor[0];
    }

    @Override
    public @NotNull @NlsContexts.ConfigurableName String getDisplayName() {
        return "FTree";
    }
}
