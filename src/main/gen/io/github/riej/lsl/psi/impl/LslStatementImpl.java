// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.riej.lsl.psi.LslTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.riej.lsl.psi.*;

public class LslStatementImpl extends ASTWrapperPsiElement implements LslStatement {

  public LslStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LslBlock getBlock() {
    return findChildByClass(LslBlock.class);
  }

  @Override
  @Nullable
  public LslDoStatement getDoStatement() {
    return findChildByClass(LslDoStatement.class);
  }

  @Override
  @Nullable
  public LslEmptyStatement getEmptyStatement() {
    return findChildByClass(LslEmptyStatement.class);
  }

  @Override
  @Nullable
  public LslExpressionStatement getExpressionStatement() {
    return findChildByClass(LslExpressionStatement.class);
  }

  @Override
  @Nullable
  public LslForStatement getForStatement() {
    return findChildByClass(LslForStatement.class);
  }

  @Override
  @Nullable
  public LslIfStatement getIfStatement() {
    return findChildByClass(LslIfStatement.class);
  }

  @Override
  @Nullable
  public LslJumpStatement getJumpStatement() {
    return findChildByClass(LslJumpStatement.class);
  }

  @Override
  @Nullable
  public LslLabelStatement getLabelStatement() {
    return findChildByClass(LslLabelStatement.class);
  }

  @Override
  @Nullable
  public LslLocalVariableDeclaration getLocalVariableDeclaration() {
    return findChildByClass(LslLocalVariableDeclaration.class);
  }

  @Override
  @Nullable
  public LslReturnStatement getReturnStatement() {
    return findChildByClass(LslReturnStatement.class);
  }

  @Override
  @Nullable
  public LslStateStatement getStateStatement() {
    return findChildByClass(LslStateStatement.class);
  }

  @Override
  @Nullable
  public LslWhileStatement getWhileStatement() {
    return findChildByClass(LslWhileStatement.class);
  }

}
