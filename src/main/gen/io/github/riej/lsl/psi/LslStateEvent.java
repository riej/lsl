// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LslStateEvent extends LslNamedElement {

  @NotNull
  List<LslArgument> getArgumentList();

  @Nullable
  LslBlock getBlock();

  @NotNull
  LslIdentifier getIdentifier();

  @Nullable
  PsiElement getParenthesesLeft();

  @Nullable
  PsiElement getParenthesesRight();

}
