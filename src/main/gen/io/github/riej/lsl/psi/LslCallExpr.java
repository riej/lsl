// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslCallExpr extends LslExpression {

  @NotNull
  List<LslExpression> getExpressionList();

  @NotNull
  LslIdentifier getIdentifier();

  @NotNull
  PsiElement getParenthesesLeft();

  @NotNull
  PsiElement getParenthesesRight();

  @NotNull
  PsiReference[] getReferences();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
