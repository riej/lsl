// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface LslTypeName extends PsiElement {

  @Nullable
  PsiElement getFloat();

  @Nullable
  PsiElement getInteger();

  @Nullable
  PsiElement getKey();

  @Nullable
  PsiElement getList();

  @Nullable
  PsiElement getQuaternion();

  @Nullable
  PsiElement getRotation();

  @Nullable
  PsiElement getString();

  @Nullable
  PsiElement getVector();

  @Nullable
  IElementType getTypeToken();

  boolean isCompatibleType(@NotNull LslTypeName t2);

}
