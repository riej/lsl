// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslUnaryExpr extends LslExpression {

  @Nullable
  LslExpression getExpression();

  @Nullable
  PsiElement getBitwiseNot();

  @Nullable
  PsiElement getBitwiseXor();

  @Nullable
  PsiElement getMinus();

  @Nullable
  PsiElement getMinusMinus();

  @Nullable
  PsiElement getNot();

  @Nullable
  PsiElement getPlus();

  @Nullable
  PsiElement getPlusPlus();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
