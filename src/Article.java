public class Article {
    private Warranty moneyBackGuarantee;
    private Warranty expressWarranty;

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
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
