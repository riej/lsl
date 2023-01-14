// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslPrintExpr extends LslExpression {

  @Nullable
  LslExpression getExpression();

  @Nullable
  PsiElement getParenthesesLeft();

  @Nullable
  PsiElement getParenthesesRight();

  @NotNull
  PsiElement getPrint();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
