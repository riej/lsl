// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslFunctionDeclaration extends LslNamedTypedElement {

  @NotNull
  List<LslArgument> getArgumentList();

  @NotNull
  LslBlock getBlock();

  @NotNull
  LslIdentifier getIdentifier();

  @Nullable
  LslTypeName getTypeName();

  @NotNull
  PsiElement getParenthesesLeft();

  @NotNull
  PsiElement getParenthesesRight();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
