/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.manchester.cs.owl.owlapi;

import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLObjectVisitor;
import org.semanticweb.owlapi.model.SWRLObjectVisitorEx;

/** @author Matthew Horridge, The University Of Manchester, Bio-Health Informatics
 *         Group, Date: 15-Jan-2007 */
public class SWRLObjectPropertyAtomImpl extends
        SWRLBinaryAtomImpl<SWRLIArgument, SWRLIArgument> implements
        SWRLObjectPropertyAtom {
    private static final long serialVersionUID = 30406L;

    /** @param predicate
     *            property
     * @param arg0
     *            subject
     * @param arg1
     *            object */
    public SWRLObjectPropertyAtomImpl(OWLObjectPropertyExpression predicate,
            SWRLIArgument arg0, SWRLIArgument arg1) {
        super(predicate, arg0, arg1);
    }

    @Override
    public OWLObjectPropertyExpression getPredicate() {
        return (OWLObjectPropertyExpression) super.getPredicate();
    }

    @Override
    public SWRLObjectPropertyAtom getSimplified() {
        OWLObjectPropertyExpression prop = getPredicate().getSimplified();
        if (prop.equals(getPredicate())) {
            return this;
        } else if (prop.isAnonymous()) {
            // Flip
            return new SWRLObjectPropertyAtomImpl(prop.getInverseProperty()
                    .getSimplified(), getSecondArgument(), getFirstArgument());
        } else {
            // No need to flip
            return new SWRLObjectPropertyAtomImpl(prop, getFirstArgument(),
                    getSecondArgument());
        }
    }

    @Override
    public void accept(OWLObjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(SWRLObjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <O> O accept(SWRLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SWRLObjectPropertyAtom)) {
            return false;
        }
        SWRLObjectPropertyAtom other = (SWRLObjectPropertyAtom) obj;
        return other.getPredicate().equals(getPredicate())
                && other.getFirstArgument().equals(getFirstArgument())
                && other.getSecondArgument().equals(getSecondArgument());
    }
}
