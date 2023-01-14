// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslConversionExpr extends LslBinaryExpr {

  @Nullable
  LslExpression getExpression();

  @NotNull
  LslTypeName getTypeName();

  @NotNull
  PsiElement getParenthesesLeft();

  @NotNull
  PsiElement getParenthesesRight();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
