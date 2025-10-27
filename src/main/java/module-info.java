module sprint2 {
    requires java.desktop;
    
    exports sprint2;
    opens sprint2;  // This allows reflection access for testing
}