// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslUnaryPostfixExpr extends LslExpression {

  @NotNull
  LslExpression getExpression();

  @Nullable
  PsiElement getMinusMinus();

  @Nullable
  PsiElement getPlusPlus();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
