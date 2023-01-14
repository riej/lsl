// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface LslJumpStatement extends PsiElement {

  @Nullable
  LslIdentifier getIdentifier();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getJump();

  @NotNull
  PsiReference[] getReferences();

}
