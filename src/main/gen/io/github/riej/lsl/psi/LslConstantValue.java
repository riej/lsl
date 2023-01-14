// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslConstantValue extends LslTypedElement {

  @Nullable
  PsiElement getFloatValue();

  @Nullable
  PsiElement getHexIntegerValue();

  @Nullable
  PsiElement getIntegerValue();

  @Nullable
  PsiElement getStringValue();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
