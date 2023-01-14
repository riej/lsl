// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslConditionalExpr extends LslBinaryExpr {

  @Nullable
  PsiElement getEq();

  @Nullable
  PsiElement getGreater();

  @Nullable
  PsiElement getGreaterEq();

  @Nullable
  PsiElement getLess();

  @Nullable
  PsiElement getLessEq();

  @Nullable
  PsiElement getNotEq();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
