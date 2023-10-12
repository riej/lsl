default {
    state_entry() {
        integer someVar; // Weak Warning: Unused variable
        integer someVar; // Error: Re-declared identifier
        llOwnerSay((string)someVar);
    }
}
