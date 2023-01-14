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
import com.intellij.psi.PsiReference;

public class LslStateStatementImpl extends ASTWrapperPsiElement implements LslStateStatement {

  public LslStateStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitStateStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LslIdentifier getIdentifier() {
    return findChildByClass(LslIdentifier.class);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @Nullable
  public PsiElement getDefault() {
    return findChildByType(DEFAULT);
  }

  @Override
  @NotNull
  public PsiElement getState() {
    return findNotNullChildByType(STATE);
  }

  @Override
  @NotNull
  public PsiReference[] getReferences() {
    return LslPsiImplUtil.getReferences(this);
  }

}
