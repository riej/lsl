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
import io.github.riej.lsl.LslPrimitiveType;

public class LslConstantValueImpl extends ASTWrapperPsiElement implements LslConstantValue {

  public LslConstantValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitConstantValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getFloatValue() {
    return findChildByType(FLOAT_VALUE);
  }

  @Override
  @Nullable
  public PsiElement getHexIntegerValue() {
    return findChildByType(HEX_INTEGER_VALUE);
  }

  @Override
  @Nullable
  public PsiElement getIntegerValue() {
    return findChildByType(INTEGER_VALUE);
  }

  @Override
  @Nullable
  public PsiElement getStringValue() {
    return findChildByType(STRING_VALUE);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
