// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslListExpr extends LslExpression {

  @NotNull
  List<LslExpression> getExpressionList();

  @NotNull
  PsiElement getBracketsLeft();

  @Nullable
  PsiElement getBracketsRight();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
