// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LslDoStatement extends PsiElement {

  @Nullable
  LslExpression getExpression();

  @Nullable
  LslStatement getStatement();

  @Nullable
  PsiElement getParenthesesLeft();

  @Nullable
  PsiElement getParenthesesRight();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getDo();

  @Nullable
  PsiElement getWhile();

}
