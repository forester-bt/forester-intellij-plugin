package com.github.besok.foresterintellijplugin.parser;

import com.github.besok.foresterintellijplugin.FTreeLanguage;
import com.github.besok.foresterintellijplugin.parser.nodes.GhostNode;
import com.github.besok.foresterintellijplugin.parser.nodes.Id;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.intellij.adaptor.xpath.XPath;

import java.util.*;


public class FTreeAstUtils {

    // tokens from the grammar
    private static List<TokenIElementType> tokens = PSIElementTypeFactory.getTokenIElementTypes(FTreeLanguage.INSTANCE);
    // rules from the grammar
    private static List<RuleIElementType> rules = PSIElementTypeFactory.getRuleIElementTypes(FTreeLanguage.INSTANCE);

    /**
     * Get the token by id like WrenLexer.{token name}
     */
    public static TokenIElementType tt(int idx) {
        return tokens.get(idx);
    }

    /**
     * Get the token by id like WrenParser.{rule name}
     */
    public static RuleIElementType rt(int idx) {
        return rules.get(idx);
    }

    public static boolean isRule(ASTNode node, int ruleIdx) {
        if (node.getElementType() instanceof RuleIElementType type) {
            return type.getRuleIndex() == ruleIdx;
        } else return false;
    }

    public static FTreeElemHelper ast(PsiElement element) {
        return new FTreeElemHelper(element);
    }

    public static class FTreeElemHelper {
        private Optional<PsiElement> elemOpt;
        private PsiElement elem;


        public FTreeElemHelper(PsiElement element) {
            this.elem = element;
            this.elemOpt =
                    Optional.of(element)
                            .filter(e -> !(e instanceof GhostNode) && !(e instanceof PsiDirectory));
        }


        public FTreeElemHelper up(){
            elemOpt = elemOpt.map(PsiElement::getParent);
            return this;
        }

        public Collection<? extends PsiElement> path(String path) {
            return elemOpt
                    .map(e -> XPath.findAll(FTreeLanguage.INSTANCE, e, path))
                    .orElseGet(Collections::emptyList);
        }

        /**
         * The method pursues to find the first encountered [[IdNode]] instance.
         * The method assumes that the first id encountered at the current level or the levels below will be
         * the unique identifier of the current element.
         * return the first encountered [[IdNode]] instance or none
         */
          public Optional<Id> id(){
              return elemOpt.flatMap(e -> {
                  if(e instanceof Id id) return Optional.of(id);
                  else if(e instanceof ANTLRPsiNode node)
                      return Arrays
                              .stream(node.getChildren())
                              .map(FTreeAstUtils::ast)
                              .map(FTreeElemHelper::id)
                              .flatMap(Optional::stream)
                              .findFirst();
                  else return Optional.empty();
              });
          }

    }
}
