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

public class LslStateEventImpl extends LslNamedElementImpl implements LslStateEvent {

  public LslStateEventImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitStateEvent(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LslArgument> getArgumentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LslArgument.class);
  }

  @Override
  @Nullable
  public LslBlock getBlock() {
    return findChildByClass(LslBlock.class);
  }

  @Override
  @NotNull
  public LslIdentifier getIdentifier() {
    return findNotNullChildByClass(LslIdentifier.class);
  }

  @Override
  @Nullable
  public PsiElement getParenthesesLeft() {
    return findChildByType(PARENTHESES_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getParenthesesRight() {
    return findChildByType(PARENTHESES_RIGHT);
  }

}
