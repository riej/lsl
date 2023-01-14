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

public class LslStateDeclarationImpl extends LslNamedElementImpl implements LslStateDeclaration {

  public LslStateDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitStateDeclaration(this);
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
  public PsiElement getState() {
    return findNotNullChildByType(STATE);
  }

}
