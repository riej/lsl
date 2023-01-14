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
import com.intellij.psi.tree.IElementType;

public class LslTypeNameImpl extends ASTWrapperPsiElement implements LslTypeName {

  public LslTypeNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitTypeName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getFloat() {
    return findChildByType(FLOAT);
  }

  @Override
  @Nullable
  public PsiElement getInteger() {
    return findChildByType(INTEGER);
  }

  @Override
  @Nullable
  public PsiElement getKey() {
    return findChildByType(KEY);
  }

  @Override
  @Nullable
  public PsiElement getList() {
    return findChildByType(LIST);
  }

  @Override
  @Nullable
  public PsiElement getQuaternion() {
    return findChildByType(QUATERNION);
  }

  @Override
  @Nullable
  public PsiElement getRotation() {
    return findChildByType(ROTATION);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

  @Override
  @Nullable
  public PsiElement getVector() {
    return findChildByType(VECTOR);
  }

  @Override
  @Nullable
  public IElementType getTypeToken() {
    return LslPsiImplUtil.getTypeToken(this);
  }

  @Override
  public boolean isCompatibleType(@NotNull LslTypeName t2) {
    return LslPsiImplUtil.isCompatibleType(this, t2);
  }

}
