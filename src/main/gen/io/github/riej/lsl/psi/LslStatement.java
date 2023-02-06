// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LslStatement extends PsiElement {

  @Nullable
  LslBlock getBlock();

  @Nullable
  LslDoStatement getDoStatement();

  @Nullable
  LslEmptyStatement getEmptyStatement();

  @Nullable
  LslExpressionStatement getExpressionStatement();

  @Nullable
  LslForStatement getForStatement();

  @Nullable
  LslIfStatement getIfStatement();

  @Nullable
  LslJumpStatement getJumpStatement();

  @Nullable
  LslLabelStatement getLabelStatement();

  @Nullable
  LslLocalVariableDeclaration getLocalVariableDeclaration();

  @Nullable
  LslReturnStatement getReturnStatement();

  @Nullable
  LslStateStatement getStateStatement();

  @Nullable
  LslWhileStatement getWhileStatement();

}
