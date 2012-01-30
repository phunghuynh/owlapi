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

import org.semanticweb.owlapi.model.OWLAnnotationValueVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationValueVisitorEx;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataVisitor;
import org.semanticweb.owlapi.model.OWLDataVisitorEx;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;

/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 26-Oct-2006<br>
 * <br>
 */
public class OWLLiteralImplDouble extends OWLObjectImpl implements OWLLiteral {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7929540842690238167L;
	private final double literal;
	private final OWLDatatype datatype;

	@SuppressWarnings("javadoc")
	public OWLLiteralImplDouble(OWLDataFactory dataFactory, double literal,
			OWLDatatype datatype) {
		super(dataFactory);
		this.literal = literal;
		this.datatype = datatype;
		hashcode = getHashCode();
	}

	private final int hashcode;

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int getHashCode() {
		int hashCode = 277;
		hashCode = hashCode * 37 + getDatatype().hashCode();
		hashCode = hashCode * 37 + (int) (literal * 65536);
		return hashCode;
	}

	public String getLiteral() {
		return Double.toString(literal);
	}

	public Double getDouble() {
		return literal;
	}

	public boolean isRDFPlainLiteral() {
		return false;
	}

	public boolean hasLang() {
		return false;
	}

	public boolean isInteger() {
		return false;
	}

	public int parseInteger() throws NumberFormatException {
		throw new NumberFormatException("this literal is not an integer but a double");
	}

	public boolean isBoolean() {
		return false;
	}

	public boolean parseBoolean() throws NumberFormatException {
		throw new NumberFormatException("this literal is not a boolean but a double");
	}

	public boolean isDouble() {
		return true;
	}

	public double parseDouble() throws NumberFormatException {
		return literal;
	}

	public boolean isFloat() {
		return false;
	}

	public float parseFloat() throws NumberFormatException {
		throw new NumberFormatException("this literal is not a float but a double");
	}

	public String getLang() {
		return "";
	}

	public boolean hasLang(String l) {
		return false;
	}

	public OWLDatatype getDatatype() {
		return datatype;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			if (obj instanceof OWLLiteralImplDouble) {
				OWLLiteralImplDouble other = (OWLLiteralImplDouble) obj;
				return literal == other.literal && datatype.equals(other.getDatatype());
			}
			if (obj instanceof OWLLiteral) {
				return datatype.equals(((OWLLiteral) obj).getDatatype())
						&& getLiteral().equals(((OWLLiteral) obj).getLiteral());
			}
		}
		return false;
	}

	public void accept(OWLDataVisitor visitor) {
		visitor.visit(this);
	}

	public <O> O accept(OWLDataVisitorEx<O> visitor) {
		return visitor.visit(this);
	}

	public void accept(OWLAnnotationValueVisitor visitor) {
		visitor.visit(this);
	}

	public <O> O accept(OWLAnnotationValueVisitorEx<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	protected int compareObjectOfSameType(OWLObject object) {
		OWLLiteral other = (OWLLiteral) object;
		int diff = getLiteral().compareTo(other.getLiteral());
		if (diff != 0) {
			return diff;
		}
		return datatype.compareTo(other.getDatatype());
	}

	public void accept(OWLObjectVisitor visitor) {
		visitor.visit(this);
	}

	public <O> O accept(OWLObjectVisitorEx<O> visitor) {
		return visitor.visit(this);
	}
}