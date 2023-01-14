// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import io.github.riej.lsl.LslPrimitiveType;

public interface LslGlobalVariableDeclaration extends LslNamedTypedElement {

  @Nullable
  LslExpression getExpression();

  @NotNull
  LslIdentifier getIdentifier();

  @NotNull
  LslTypeName getTypeName();

  @Nullable
  PsiElement getAssign();

  @NotNull
  PsiElement getSemicolon();

  @NotNull
  LslPrimitiveType getPrimitiveType();

}
