// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LslForStatement extends PsiElement {

  @NotNull
  List<LslExpression> getExpressionList();

  @Nullable
  LslStatement getStatement();

  @Nullable
  PsiElement getParenthesesLeft();

  @Nullable
  PsiElement getParenthesesRight();

  @NotNull
  PsiElement getFor();

}
