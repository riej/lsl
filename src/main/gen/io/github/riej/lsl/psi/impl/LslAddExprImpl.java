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

public class LslAddExprImpl extends LslBinaryExprImpl implements LslAddExpr {

  public LslAddExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitAddExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getBitwiseOr() {
    return findChildByType(BITWISE_OR);
  }

  @Override
  @Nullable
  public PsiElement getBitwiseXor() {
    return findChildByType(BITWISE_XOR);
  }

  @Override
  @Nullable
  public PsiElement getMinus() {
    return findChildByType(MINUS);
  }

  @Override
  @Nullable
  public PsiElement getPlus() {
    return findChildByType(PLUS);
  }

}
