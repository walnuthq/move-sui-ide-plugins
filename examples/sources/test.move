module test_addr::m {
    #[test]
    public fun test_func(): u8 {
        let x = 40;
        let y = copy x; // Use copy instead of move
        let _ = x;
        y + 2
    }

    #[test]
    public fun test_result() {
        let result = test_func();
        assert!(result == 42, 1000);
    }
}