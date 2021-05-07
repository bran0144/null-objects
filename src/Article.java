public class Article {
    private Warranty moneyBackGuarantee;
    private Warranty expressWarranty;

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        if (moneyBackGuarantee == null) throw new IllegalArgumentException();
        if (expressWarranty == null) throw new IllegalArgumentException();

        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
    }

    public Warranty getExpressWarranty() {
        return expressWarranty;
    }
    public Warranty getMoneyBackGuarantee() {
        return moneyBackGuarantee;
    }
}
