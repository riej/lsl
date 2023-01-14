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
import io.github.riej.lsl.LslPrimitiveType;

public class LslLValueImpl extends ASTWrapperPsiElement implements LslLValue {

  public LslLValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitLValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LslIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LslIdentifier.class);
  }

  @Override
  @Nullable
  public PsiElement getDot() {
    return findChildByType(DOT);
  }

  @Override
  @NotNull
  public PsiReference[] getReferences() {
    return LslPsiImplUtil.getReferences(this);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
