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

public class LslFunctionDeclarationImpl extends LslNamedElementImpl implements LslFunctionDeclaration {

  public LslFunctionDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitFunctionDeclaration(this);
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
  @NotNull
  public LslBlock getBlock() {
    return findNotNullChildByClass(LslBlock.class);
  }

  @Override
  @NotNull
  public LslIdentifier getIdentifier() {
    return findNotNullChildByClass(LslIdentifier.class);
  }

  @Override
  @Nullable
  public LslTypeName getTypeName() {
    return findChildByClass(LslTypeName.class);
  }

  @Override
  @NotNull
  public PsiElement getParenthesesLeft() {
    return findNotNullChildByType(PARENTHESES_LEFT);
  }

  @Override
  @NotNull
  public PsiElement getParenthesesRight() {
    return findNotNullChildByType(PARENTHESES_RIGHT);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
