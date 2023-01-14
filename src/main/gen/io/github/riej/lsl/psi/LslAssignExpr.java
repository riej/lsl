// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslAssignExpr extends LslExpression {

  @Nullable
  LslExpression getExpression();

  @NotNull
  LslLValue getLValue();

  @Nullable
  PsiElement getAssign();

  @Nullable
  PsiElement getDivideAssign();

  @Nullable
  PsiElement getMinusAssign();

  @Nullable
  PsiElement getModulusAssign();

  @Nullable
  PsiElement getMultipleAssign();

  @Nullable
  PsiElement getPlusAssign();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
