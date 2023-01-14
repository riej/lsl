// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface LslDefaultStateDeclaration extends LslNamedElement {

  @NotNull
  List<LslStateEvent> getStateEventList();

  @Nullable
  PsiElement getBracesLeft();

  @Nullable
  PsiElement getBracesRight();

  @NotNull
  PsiElement getDefault();

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  PsiElement getIdentifier();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  ItemPresentation getPresentation();

}
