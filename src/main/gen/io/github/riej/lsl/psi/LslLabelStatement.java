// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LslLabelStatement extends LslNamedElement {

  @Nullable
  LslIdentifier getIdentifier();

  @NotNull
  PsiElement getLabel();

  @Nullable
  PsiElement getSemicolon();

}
