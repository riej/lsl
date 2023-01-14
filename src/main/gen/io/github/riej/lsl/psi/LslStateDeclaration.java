// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LslStateDeclaration extends LslNamedElement {

  @Nullable
  LslIdentifier getIdentifier();

  @NotNull
  List<LslStateEvent> getStateEventList();

  @Nullable
  PsiElement getBracesLeft();

  @Nullable
  PsiElement getBracesRight();

  @NotNull
  PsiElement getState();

}
