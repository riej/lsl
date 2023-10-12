integer GLOBAL_LIST_INDEX = 0;
list GLOBAL_LIST = [10];

integer someFunction(integer param) {
    return param + llList2Integer(GLOBAL_LIST, GLOBAL_LIST_INDEX);
}

default {
    state_entry() {
        integer someVar;
        someVar++;
        llOwnerSay("Some message: " + (string)someFunction(++someVar));
    }
}
