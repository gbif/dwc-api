/*
 * Copyright 2024 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.dwc.terms;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;

/**
 * All GenSC (Genomics Standards Consortium) Minimum Information about any Sequence (MIxS) terms
 * with namespace https://w3id.org/mixs/ or https://w3id.org/gensc/terms/MIXS:.
 *
 * Note URIs here must use the https://w3id.org/mixs/0000001 form, but we keep the simple name for casual use
 * (the enum names, the simpleName for CSV headers etc).
 *
 * See https://genomicsstandardsconsortium.github.io/mixs/
 *
 * See https://github.com/GenomicsStandardsConsortium/mixs/blob/main/src/mixs/schema/mixs.yaml
 */
public enum MixsTerm implements Term, AlternativeNames, Serializable {
  samp_size("0000001"),
  samp_collect_device("0000002"),
  isol_growth_condt("0000003"),
  contam_screen_input("0000005"),
  wga_amp_kit("0000006"),
  experimental_factor("0000008"),
  env_broad_scale("0000012"),
  env_local_scale("0000013"),
  env_medium("0000014"),
  rel_to_oxygen("0000015"),
  samp_mat_process("0000016"),
  size_frac("0000017"),
  subspecf_gen_lin("0000020"),
  ploidy("0000021"),
  num_replicons("0000022"),
  extrachrom_elements("0000023"),
  estimated_size("0000024"),
  ref_biomaterial("0000025"),
  source_mat_id("0000026"),
  pathogenicity("0000027"),
  biotic_relationship("0000028"),
  specific_host("0000029"),
  host_spec_range("0000030"),
  host_disease_stat("0000031"),
  trophic_level("0000032"),
  propagation("0000033"),
  encoded_traits("0000034"),
  source_uvig("0000035"),
  virus_enrich_appr("0000036"),
  nucl_acid_ext("0000037"),
  nucl_acid_amp("0000038"),
  lib_size("0000039"),
  lib_reads_seqd("0000040"),
  lib_layout("0000041", "http://gensc.org/ns/mixs/lib_const_meth"),
  lib_vector("0000042"),
  lib_screen("0000043"),
  target_gene("0000044"),
  target_subfragment("0000045"),
  pcr_primers("0000046"),
  mid("0000047"),
  adapters("0000048"),
  pcr_cond("0000049"),
  seq_meth("0000050"),
  seq_quality_check("0000051"),
  chimera_check("0000052"),
  tax_ident("0000053"),
  sc_lysis_method("0000054"),
  wga_amp_appr("0000055"),
  assembly_qual("0000056", "http://gensc.org/ns/mixs/finishing_strategy"),
  assembly_name("0000057", "http://gensc.org/ns/mixs/assembly"),
  assembly_software("0000058"),
  annot("0000059", "http://gensc.org/ns/mixs/annot_source"),
  number_contig("0000060"),
  feat_pred("0000061"),
  ref_db("0000062"),
  sim_search_meth("0000063"),
  tax_class("0000064"),
  _16s_recover("0000065", "16s_recover"),
  _16s_recover_software("0000066", "16s_recover_software"),
  trnas("0000067"),
  trna_ext_software("0000068"),
  compl_score("0000069"),
  compl_software("0000070"),
  compl_appr("0000071"),
  contam_score("0000072"),
  contam_screen_param("0000073"),
  decontam_software("0000074"),
  sort_tech("0000075"),
  sc_lysis_approach("0000076"),
  bin_param("0000077"),
  bin_software("0000078"),
  reassembly_bin("0000079"),
  mag_cov_software("0000080"),
  vir_ident_software("0000081"),
  pred_genome_type("0000082"),
  pred_genome_struc("0000083"),
  detec_type("0000084"),
  otu_class_appr("0000085"),
  otu_seq_comp_appr("0000086"),
  otu_db("0000087"),
  host_pred_appr("0000088"),
  host_pred_est_acc("0000089"),
  sop("0000090"),
  associated_resource("0000091"),
  project_name("0000092"),
  samp_vol_we_dna_ext("0000111"),
  sieving("0000322"),
  pool_dna_extracts("0000325"),
  samp_name("0001107"),
  samp_collect_method("0001225");

  private static final String PREFIX = "mixs";

  // This is the current and preferred namespace. The URI given on https://w3id.org/mixs/0000001 (which redirects to
  // https://genomicsstandardsconsortium.github.io/mixs/0000001/) is itself, https://w3id.org/mixs/0000001.
  private static final String NS = "https://w3id.org/mixs/";

  // This was the namespace before 2022, and was used for the DNA extension.
  private static final String OLD_NS = "https://w3id.org/gensc/terms/MIXS:";

  // This was a proposed namespace, or may have been the real namespace. It is used in the GGBN extensions.
  // https://bioregistry.io/registry/mixs says it was temporary, it's possible it was invented by TDWG people.
  private static final String VERY_OLD_NS = "http://gensc.org/ns/mixs/";

  private static final URI NS_URI = URI.create(NS);
  private final String mixsIdentifier;
  public final String[] alternatives;

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public String simpleName() {
    if (this == _16s_recover) {
      return "16s_recover";
    }
    if (this == _16s_recover_software) {
      return "16s_recover_software";
    }
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return alternatives;
  }

  @Override
  public String qualifiedName() {
    return namespace() + mixsIdentifier;
  }

  @Override
  public boolean isClass() {
    return false;
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  MixsTerm(String mixsIdentifier, String... alternatives) {
    this.mixsIdentifier = mixsIdentifier;
    this.alternatives = Arrays.copyOf(alternatives, alternatives.length + 3);
    this.alternatives[alternatives.length] = name();
    this.alternatives[alternatives.length+1] = OLD_NS + mixsIdentifier;
    this.alternatives[alternatives.length+2] = VERY_OLD_NS + name();
  }
}
