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

public class LslLiteralImpl extends LslExpressionImpl implements LslLiteral {

  public LslLiteralImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitLiteral(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LslConstantValue getConstantValue() {
    return findChildByClass(LslConstantValue.class);
  }

  @Override
  @Nullable
  public LslLValue getLValue() {
    return findChildByClass(LslLValue.class);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
