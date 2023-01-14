// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.riej.lsl.psi.LslTypes.*;
import io.github.riej.lsl.psi.*;

public class LslMulExprImpl extends LslBinaryExprImpl implements LslMulExpr {

  public LslMulExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitMulExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getBitwiseAnd() {
    return findChildByType(BITWISE_AND);
  }

  @Override
  @Nullable
  public PsiElement getDivide() {
    return findChildByType(DIVIDE);
  }

  @Override
  @Nullable
  public PsiElement getModulus() {
    return findChildByType(MODULUS);
  }

  @Override
  @Nullable
  public PsiElement getMultiple() {
    return findChildByType(MULTIPLE);
  }

  @Override
  @Nullable
  public PsiElement getShiftLeft() {
    return findChildByType(SHIFT_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getShiftRight() {
    return findChildByType(SHIFT_RIGHT);
  }

}
