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

public class LslGlobalVariableDeclarationImpl extends LslNamedElementImpl implements LslGlobalVariableDeclaration {

  public LslGlobalVariableDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitGlobalVariableDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LslExpression getExpression() {
    return findChildByClass(LslExpression.class);
  }

  @Override
  @NotNull
  public LslIdentifier getIdentifier() {
    return findNotNullChildByClass(LslIdentifier.class);
  }

  @Override
  @NotNull
  public LslTypeName getTypeName() {
    return findNotNullChildByClass(LslTypeName.class);
  }

  @Override
  @Nullable
  public PsiElement getAssign() {
    return findChildByType(ASSIGN);
  }

  @Override
  @NotNull
  public PsiElement getSemicolon() {
    return findNotNullChildByType(SEMICOLON);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
