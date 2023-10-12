// Redeclared variable
integer aCustomGlobalVar;
integer aCustomGlobalVar = 42;

integer aCustomFunction() {
    // Reference to a nonexistent variable
    aCustomNonExistent;

    // Suggestions: aCustomGlobalVar, aCustomFunction, aCustomLocalVar
    integer aCustomLocalVar = aCustom/*caret*/;
}

default {}

state aCustomState {}
