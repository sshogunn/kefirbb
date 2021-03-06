package org.kefirsf.bb.conf;

import org.kefirsf.bb.util.Exceptions;
import org.kefirsf.bb.util.Utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Scope definition. Scope is a set of codes which work in defined places.
 *
 * @author Vitaliy Samolovskih aka Kefir
 */
public class Scope {
    /**
     * Default name for root scope. If ROOT scope not defined in configuration
     * then all codes add to default ROOT scope.
     */
    public static final String ROOT = "ROOT";

    /**
     * By default we don't ignore text outer codes.
     */
    public static final boolean DEFAULT_IGNORE_TEXT = false;

    /**
     * Scope name.
     */
    private String name;

    /**
     * Parent scope. Scope inherit all codes of his parent scope.
     */
    private Scope parent;

    /**
     * Ignore or not text outer the codes. By default false.
     */
    private boolean ignoreText = DEFAULT_IGNORE_TEXT;

    /**
     * Codes of scope.
     */
    private Set<Code> codes = new HashSet<Code>();

    /**
     * Create a scope with random name.
     */
    public Scope() {
        name = Utils.generateRandomName();
    }

    /**
     * Create a scope with a name.
     *
     * @param name name of scope
     */
    public Scope(String name) {
        this.name = name;
        this.parent = null;
        this.ignoreText = false;
    }

    /**
     * Create a scope.
     *
     * @param name       scope name
     * @param ignoreText define ignore or not text outer the codes.
     */
    public Scope(String name, boolean ignoreText) {
        this.name = name;
        this.parent = null;
        this.ignoreText = ignoreText;
    }

    /**
     * Create a scope.
     *
     * @param name       scope name
     * @param parent     parent scope
     * @param ignoreText define copy or not text outer the codes.
     */
    public Scope(String name, Scope parent, boolean ignoreText) {
        this.name = name;
        this.parent = parent;
        this.ignoreText = ignoreText;
    }

    /**
     * Get scope name.
     *
     * @return scope name
     */
    public String getName() {
        return name;
    }

    /**
     * Set scope name
     *
     * @param name new scope name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get parent scope. The scope inherit all codes from parent.
     *
     * @return scope
     */
    public Scope getParent() {
        return parent;
    }

    /**
     * Set parent scope. The scope inherit all codes from parent.
     *
     * @param parent scope
     */
    public void setParent(Scope parent) {
        this.parent = parent;
    }

    /**
     * How to use text outer codes.
     *
     * @return true ignore text
     *         false copy, default
     */
    public boolean isIgnoreText() {
        return ignoreText;
    }

    /**
     * How to use text outer codes.
     *
     * @param ignoreText true - ignore text, false - copy. By default is false.
     */
    public void setIgnoreText(boolean ignoreText) {
        this.ignoreText = ignoreText;
    }

    /**
     * Get codes of scope. Only of this scope not of parent.
     *
     * @return code set
     */
    public Set<Code> getCodes() {
        return Collections.unmodifiableSet(codes);
    }

    /**
     * Set codes.
     *
     * @param codes set of codes
     */
    public void setCodes(Set<Code> codes) {
        Exceptions.nullArgument("codes", codes);

        this.codes.clear();
        this.codes.addAll(codes);
    }

    /**
     * Add a code to scope.
     *
     * @param code code
     */
    public void addCode(Code code) {
        codes.add(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scope scope = (Scope) o;

        return name.equals(scope.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
