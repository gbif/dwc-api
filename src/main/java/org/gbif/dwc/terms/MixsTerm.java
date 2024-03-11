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

/**
 * All MiXS terms with namespace http://gensc.org/ns/mixs/.
 *
 * Note URIs here must use the MIXS:0000001 form, but we keep the simple name for casual use (the enum names,
 * the simpleName for CSV headers etc).
 *
 * See https://genomicsstandardsconsortium.github.io/mixs/
 *
 * See https://github.com/GenomicsStandardsConsortium/mixs/blob/main/src/mixs/schema/mixs.yaml
 */
public enum MixsTerm implements Term, AlternativeNames, Serializable {
  samp_size("MIXS:0000001"),
  samp_collect_device("MIXS:0000002"),
  isol_growth_condt("MIXS:0000003"),
  contam_screen_input("MIXS:0000005"),
  wga_amp_kit("MIXS:0000006"),
  experimental_factor("MIXS:0000008"),
  env_broad_scale("MIXS:0000012"),
  env_local_scale("MIXS:0000013"),
  env_medium("MIXS:0000014"),
  rel_to_oxygen("MIXS:0000015"),
  samp_mat_process("MIXS:0000016"),
  size_frac("MIXS:0000017"),
  subspecf_gen_lin("MIXS:0000020"),
  ploidy("MIXS:0000021"),
  num_replicons("MIXS:0000022"),
  extrachrom_elements("MIXS:0000023"),
  estimated_size("MIXS:0000024"),
  ref_biomaterial("MIXS:0000025"),
  source_mat_id("MIXS:0000026"),
  pathogenicity("MIXS:0000027"),
  biotic_relationship("MIXS:0000028"),
  specific_host("MIXS:0000029"),
  host_spec_range("MIXS:0000030"),
  host_disease_stat("MIXS:0000031"),
  trophic_level("MIXS:0000032"),
  propagation("MIXS:0000033"),
  encoded_traits("MIXS:0000034"),
  source_uvig("MIXS:0000035"),
  virus_enrich_appr("MIXS:0000036"),
  nucl_acid_ext("MIXS:0000037"),
  nucl_acid_amp("MIXS:0000038"),
  lib_size("MIXS:0000039"),
  lib_reads_seqd("MIXS:0000040"),
  lib_layout("MIXS:0000041", "lib_const_meth"),
  lib_vector("MIXS:0000042"),
  lib_screen("MIXS:0000043"),
  target_gene("MIXS:0000044"),
  target_subfragment("MIXS:0000045"),
  pcr_primers("MIXS:0000046"),
  mid("MIXS:0000047"),
  adapters("MIXS:0000048"),
  pcr_cond("MIXS:0000049"),
  seq_meth("MIXS:0000050"),
  seq_quality_check("MIXS:0000051"),
  chimera_check("MIXS:0000052"),
  tax_ident("MIXS:0000053"),
  sc_lysis_method("MIXS:0000054"),
  wga_amp_appr("MIXS:0000055"),
  assembly_qual("MIXS:0000056", "finishing_strategy"),
  assembly_name("MIXS:0000057", "assembly"),
  assembly_software("MIXS:0000058"),
  annot("MIXS:0000059", "annot_source"),
  number_contig("MIXS:0000060"),
  feat_pred("MIXS:0000061"),
  ref_db("MIXS:0000062"),
  sim_search_meth("MIXS:0000063"),
  tax_class("MIXS:0000064"),
  _16s_recover("MIXS:0000065", "16s_recover"),
  _16s_recover_software("MIXS:0000066", "16s_recover_software"),
  trnas("MIXS:0000067"),
  trna_ext_software("MIXS:0000068"),
  compl_score("MIXS:0000069"),
  compl_software("MIXS:0000070"),
  compl_appr("MIXS:0000071"),
  contam_score("MIXS:0000072"),
  contam_screen_param("MIXS:0000073"),
  decontam_software("MIXS:0000074"),
  sort_tech("MIXS:0000075"),
  sc_lysis_approach("MIXS:0000076"),
  bin_param("MIXS:0000077"),
  bin_software("MIXS:0000078"),
  reassembly_bin("MIXS:0000079"),
  mag_cov_software("MIXS:0000080"),
  vir_ident_software("MIXS:0000081"),
  pred_genome_type("MIXS:0000082"),
  pred_genome_struc("MIXS:0000083"),
  detec_type("MIXS:0000084"),
  otu_class_appr("MIXS:0000085"),
  otu_seq_comp_appr("MIXS:0000086"),
  otu_db("MIXS:0000087"),
  host_pred_appr("MIXS:0000088"),
  host_pred_est_acc("MIXS:0000089"),
  sop("MIXS:0000090"),
  associated_resource("MIXS:0000091"),
  project_name("MIXS:0000092"),
  samp_vol_we_dna_ext("MIXS:0000111"),
  sieving("MIXS:0000322"),
  pool_dna_extracts("MIXS:0000325"),
  samp_name("MIXS:0001107"),
  samp_collect_method("MIXS:0001225");

  private static final String PREFIX = "mixs";
  private static final String NS = "https://w3id.org/mixs/";
  private static final URI NS_URI = URI.create(NS);
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
  public String prefixedName() {
    return prefix() + ":" + alternatives[0];
  }

  /**
   * @return true if the GGBN term is defining a class instead of a property, e.g. Amplification
   */
  @Override
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  MixsTerm(String... alternatives) {
    this.alternatives = alternatives;
  }
}
