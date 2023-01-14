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

public class LslVectorOrQuaternionExprImpl extends LslExpressionImpl implements LslVectorOrQuaternionExpr {

  public LslVectorOrQuaternionExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitVectorOrQuaternionExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LslExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LslExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getGreater() {
    return findNotNullChildByType(GREATER);
  }

  @Override
  @NotNull
  public PsiElement getLess() {
    return findNotNullChildByType(LESS);
  }

  @Override
  public boolean isVector() {
    return LslPsiImplUtil.isVector(this);
  }

  @Override
  public boolean isQuaternion() {
    return LslPsiImplUtil.isQuaternion(this);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
