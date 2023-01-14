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
import com.intellij.navigation.ItemPresentation;

public class LslDefaultStateDeclarationImpl extends ASTWrapperPsiElement implements LslDefaultStateDeclaration {

  public LslDefaultStateDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitDefaultStateDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LslStateEvent> getStateEventList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LslStateEvent.class);
  }

  @Override
  @Nullable
  public PsiElement getBracesLeft() {
    return findChildByType(BRACES_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getBracesRight() {
    return findChildByType(BRACES_RIGHT);
  }

  @Override
  @NotNull
  public PsiElement getDefault() {
    return findNotNullChildByType(DEFAULT);
  }

  @Override
  @NotNull
  public PsiElement getNameIdentifier() {
    return LslPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return LslPsiImplUtil.getIdentifier(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return LslPsiImplUtil.setName(this, name);
  }

  @Override
  @NotNull
  public ItemPresentation getPresentation() {
    return LslPsiImplUtil.getPresentation(this);
  }

}
