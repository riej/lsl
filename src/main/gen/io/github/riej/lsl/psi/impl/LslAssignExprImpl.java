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
import io.github.riej.lsl.LslPrimitiveType;

public class LslAssignExprImpl extends LslExpressionImpl implements LslAssignExpr {

  public LslAssignExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitAssignExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LslExpression getExpression() {
    return findChildByClass(LslExpression.class);
  }

  @Override
  @NotNull
  public LslLValue getLValue() {
    return findNotNullChildByClass(LslLValue.class);
  }

  @Override
  @Nullable
  public PsiElement getAssign() {
    return findChildByType(ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getDivideAssign() {
    return findChildByType(DIVIDE_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getMinusAssign() {
    return findChildByType(MINUS_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getModulusAssign() {
    return findChildByType(MODULUS_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getMultipleAssign() {
    return findChildByType(MULTIPLE_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getPlusAssign() {
    return findChildByType(PLUS_ASSIGN);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
