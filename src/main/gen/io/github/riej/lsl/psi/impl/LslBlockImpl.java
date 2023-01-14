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

public class LslBlockImpl extends ASTWrapperPsiElement implements LslBlock {

  public LslBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LslStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LslStatement.class);
  }

  @Override
  @NotNull
  public PsiElement getBracesLeft() {
    return findNotNullChildByType(BRACES_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getBracesRight() {
    return findChildByType(BRACES_RIGHT);
  }

}
