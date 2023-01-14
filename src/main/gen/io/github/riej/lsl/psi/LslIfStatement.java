// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LslIfStatement extends PsiElement {

  @Nullable
  LslElseStatement getElseStatement();

  @Nullable
  LslExpression getExpression();

  @Nullable
  LslStatement getStatement();

  @Nullable
  PsiElement getParenthesesLeft();

  @Nullable
  PsiElement getParenthesesRight();

  @NotNull
  PsiElement getIf();

}
