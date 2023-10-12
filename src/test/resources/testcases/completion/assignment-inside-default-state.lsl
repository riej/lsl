// Redeclared variable
integer aCustomGlobalVar;
integer aCustomGlobalVar = 42;

default {
    state_entry()
    {
        // Reference to a nonexistent variable
        aCustomNonExistent;

        // Suggestions: aCustomGlobalVar, aCustomLocalVar
        integer aCustomLocalVar = aCustom/*caret*/;
    }
}

state aCustomState {}
